/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.skpm.annotations;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.Set;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;

/**
 *
 * @author Suresh Pun <suresh.punmagar@syntechnepal.com>
 */
@SupportedAnnotationTypes(value={ "javax.faces.bean.ViewScoped"} )
//@SupportedSourceVersion( SourceVersion.RELEASE_7 )
@SupportedOptions(value = {"debug","fullyAnnotationConfigured"})
public class ViewScopedAnnotationProcessor extends AbstractProcessor {
  @Override
  public boolean process(final Set< ? extends TypeElement > annotations, 
      final RoundEnvironment roundEnv) {
         
       /* for( final Element element: roundEnv.getElementsAnnotatedWith( ViewScoped.class ) ) {
          if( element instanceof TypeElement ) {
            final TypeElement typeElement = ( TypeElement )element;

            for( final Element eclosedElement: typeElement.getEnclosedElements() ) {
           if( eclosedElement instanceof ExecutableElement ) {
               final ExecutableElement variableElement = ( ExecutableElement )eclosedElement;
               ElementKind kind = variableElement.getKind();
               if( kind==ElementKind.METHOD &&  !variableElement.getModifiers().contains( Modifier.FINAL ) ) {
                 processingEnv.getMessager().printMessage( Diagnostic.Kind.ERROR,
                   String.format( "Class '%s' is annotated as @ViewScoped, but method '%s' is not declared as final", 
                     typeElement.getSimpleName(), variableElement.getSimpleName()            
                   ) 
                 );                     
               }
             }
           }
        }
        // Claiming that annotations have been processed by this processor 
      }*/
        return true;
  }
}
