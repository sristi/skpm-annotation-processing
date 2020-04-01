/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.skpm.annotations;

import java.util.List;
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
import javax.faces.bean.ViewScoped;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;

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
        for(Element element:elements){
            checkIt(element, ViewScoped.class.getName());
        }
            //check(elements, ViewScoped.class.getName());

            /*Set<? extends Element> ejbs = roundEnv.getElementsAnnotatedWith( Stateless.class );
        check(ejbs, Stateless.class.getName());*/
        return true;
  }

    private void check(Set<? extends Element> elements, String annotationName) {
        for( final Element element: elements ) {
          if( element instanceof TypeElement ) {
            final TypeElement typeElement = ( TypeElement )element;

            //checkAll(typeElement.getEnclosedElements(), typeElement, annotationName);
        }
        // Claiming that annotations have been processed by this processor 
      }
    }

    private void checkAll(List<? extends Element> enclosedElements, TypeElement typeElement, String annotationName) {
        for( final Element enclosedElement: enclosedElements ) {
            if( enclosedElement instanceof ExecutableElement && 
                    (enclosedElement.getModifiers().contains(Modifier.PUBLIC) && 
                    !enclosedElement.getModifiers().contains(Modifier.STATIC) &&
                    !enclosedElement.getModifiers().contains(Modifier.FINAL) ) && 
                    !enclosedElement.getSimpleName().toString().startsWith("get") &&
                    !enclosedElement.getSimpleName().toString().startsWith("is") &&
                    !enclosedElement.getSimpleName().toString().startsWith("set") ) {
                final ExecutableElement variableElement = ( ExecutableElement )enclosedElement;
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
    
    private void checkIt(Element element, String annotationName) {
          if( element instanceof TypeElement ) {
            final TypeElement typeElement = ( TypeElement )element;
            checkAll(typeElement.getEnclosedElements(), typeElement, annotationName);
            //checkAll(typeElement.getEnclosingElement().getEnclosedElements(),typeElement, annotationName);
        }
    }
}
