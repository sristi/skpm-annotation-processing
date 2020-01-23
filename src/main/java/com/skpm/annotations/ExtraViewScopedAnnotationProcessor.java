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
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;

/**
 *
 * @author Suresh Pun <suresh.punmagar@syntechnepal.com>
 */
@SupportedAnnotationTypes(value={ "com.skpm.annotations.ExtraViewScoped"} )
//@SupportedSourceVersion( SourceVersion.RELEASE_7 )
@SupportedOptions(value = {"debug","fullyAnnotationConfigured"})
public class ExtraViewScopedAnnotationProcessor extends AbstractProcessor {
  @Override
  public boolean process(final Set< ? extends TypeElement > annotations, 
      final RoundEnvironment roundEnv) {
         
        for( final Element element: roundEnv.getElementsAnnotatedWith( ExtraViewScoped.class ) ) {
          if( element instanceof TypeElement ) {
            final TypeElement typeElement = ( TypeElement )element;

            for( final Element eclosedElement: typeElement.getEnclosedElements() ) {
           if( eclosedElement instanceof ExecutableElement ) {
               final ExecutableElement variableElement = ( ExecutableElement )eclosedElement;
               ElementKind kind = variableElement.getKind();
               try {
                   Class annotationClass = Class.forName("com.synergytechsoft.security.WithResourceAction");
                   
                   if(kind==ElementKind.METHOD && variableElement.getAnnotation(annotationClass) ==null){
                       processingEnv.getMessager().printMessage( Diagnostic.Kind.ERROR,
                        String.format( "Class '%s' is annotated as @ExtraViewScoped, but method '%s' is not annotated as @WithResourceAction", 
                          typeElement.getSimpleName(), variableElement.getSimpleName() ) );
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
        return true;
  }
}
