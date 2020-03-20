/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.skpm.annotations;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.ejb.Stateless;
import javax.faces.bean.ViewScoped;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;

/**
 *
 * @author Suresh Pun
 */
@SupportedAnnotationTypes(value={ "javax.faces.bean.ViewScoped"})//,"javax.ejb.Stateless"} )
//@SupportedSourceVersion( SourceVersion.RELEASE_7 )
@SupportedOptions(value = {"debug","fullyAnnotationConfigured"})
public class ExtraViewScopedAnnotationProcessor extends AbstractProcessor {
  @Override
  public boolean process(final Set< ? extends TypeElement > annotations, 
      final RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith( ViewScoped.class );
        //elements.addAll(roundEnv.getElementsAnnotatedWith( Stateless.class ));
        check(elements, ViewScoped.class.getName());
        
        /*Set<? extends Element> ejbs = roundEnv.getElementsAnnotatedWith( Stateless.class );
        check(ejbs, Stateless.class.getName());*/
        return true;
  }

    private void check(Set<? extends Element> elements, String annotationName) {
        for( final Element element: elements ) {
          if( element instanceof TypeElement ) {
            final TypeElement typeElement = ( TypeElement )element;

            for( final Element eclosedElement: typeElement.getEnclosedElements() ) {
                if( eclosedElement instanceof ExecutableElement ) {
                    final ExecutableElement variableElement = ( ExecutableElement )eclosedElement;
                    ElementKind kind = variableElement.getKind();
                    try {
                        Class annotationClass = Class.forName("com.synergytechsoft.security.WithResourceAction");
                        Class postConstructClass = Class.forName("javax.annotation.PostConstruct");
                        Class preDestroyClass = Class.forName("javax.annotation.PreDestroy");
                        if(kind==ElementKind.METHOD && variableElement.getAnnotation(annotationClass) ==null 
                                && variableElement.getAnnotation(postConstructClass)==null
                                && variableElement.getAnnotation(preDestroyClass)==null ){
                            processingEnv.getMessager().printMessage( Diagnostic.Kind.ERROR,
                             String.format( "Class '%s' is annotated as '%s' , but method '%s' is not annotated as @WithResourceAction", 
                               typeElement.getQualifiedName(),annotationName, variableElement.getSimpleName() ) );
                        }

                        /*variableElement.getAnnotation(annotationClass);

                         if(( kind==ElementKind.METHOD ) &&  !variableElement.getModifiers().contains( Modifier.FINAL ) ) {
                           processingEnv.getMessager().printMessage( Diagnostic.Kind.ERROR,
                             String.format( "Class '%s' is annotated as @ExtraViewScoped, but method '%s' is not declared as final", 
                               typeElement.getSimpleName(), variableElement.getSimpleName()            
                             ) 
                           );                     
                         }*/
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ExtraViewScopedAnnotationProcessor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  }
                }
        }
        // Claiming that annotations have been processed by this processor 
      }
    }
}
