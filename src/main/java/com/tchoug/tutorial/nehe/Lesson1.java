package com.tchoug.tutorial.nehe;

	import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
/**
 * Created 18-oct.-2014
 *
 * @author Tig
 *
 * Lesson1.java
 * author: Walter Milner
 * from Brian Paul (converted to Java by Ron Cemer and Sven Goethel)
 *
 * NeHe Lesson 1
 */
public class Lesson1 implements GLEventListener {

	public void init(GLAutoDrawable drawable) {

// like int InitGL(GLvoid)
// but does not return an error code?
// part of the GLEventListener interface
		GL2 gl = drawable.getGL().getGL2();
// Setup the drawing area and shading mode
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glClearDepth(1.0f); // depth buffer
		gl.glEnable(GL2.GL_DEPTH_TEST); // Enables Depth Testing
		gl.glDepthFunc(GL2.GL_LEQUAL); // type of depth test
		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
	}

	@Override
	public void dispose(GLAutoDrawable glAutoDrawable) {

	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int
			height)
	{
// like GLvoid ReSizeGLScene(GLsizei width, GLsizei height)
// reshape is part of the GLEventListener interface, and is called during
// first re-paint after a resize
		GL2 gl = drawable.getGL().getGL2();

		GLU glu = new GLU();

		if (height <= 0) { // avoid a divide by zero error!
			height = 1;
		}
		final float h = (float) width / (float) height;
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(45.0f, h, 0.1f, 100.0f);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		// like int DrawGLScene(GLvoid)
		GL2 gl = drawable.getGL().getGL2();

		// Clear the drawing area
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		// Reset the current matrix to the “identity”
		gl.glLoadIdentity();
		gl.glTranslatef(-1.5f,0.0f,-6.0f);
		gl.glBegin(GL2.GL_TRIANGLES);                        // Drawing Using Triangles
		gl.glVertex3f( 0.0f, 1.0f, 0.0f);                // Top
		gl.glVertex3f(-1.0f,-1.0f, 0.0f);                // Bottom Left
		gl.glVertex3f( 1.0f,-1.0f, 0.0f);                // Bottom Right
		gl.glEnd();

		gl.glTranslatef(3.0f,0.0f,-0.0f);
		gl.glBegin(GL2.GL_QUADS);                        // Draw A Quad
		gl.glVertex3f(-1.0f, 1.0f, 0.0f);                // Top Left
		gl.glVertex3f( 1.0f, 1.0f, 0.0f);                // Top Right
		gl.glVertex3f( 1.0f,-1.0f, 0.0f);                // Bottom Right
		gl.glVertex3f(-1.0f,-1.0f, 0.0f);                // Bottom Left
		gl.glEnd();

	}
}