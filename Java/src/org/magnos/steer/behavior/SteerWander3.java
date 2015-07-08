package org.magnos.steer.behavior;

import org.magnos.steer.Steer;
import org.magnos.steer.SteerMath;
import org.magnos.steer.SteerSubject;
import org.magnos.steer.vec.Vec3;


/**
 * A steering behavior that can smoothly move the subject in a random direction
 * at maximum acceleration.
 */
public class SteerWander3 extends AbstractSteer<Vec3, SteerWander3>
{

	public float yaw;
	public float pitch;
	public float radius;
	public float distance;
	public float wander;

    public SteerWander3(float magnitude, float yaw, float pitch, float radius, float distance, float wander)
    {
        this( magnitude, magnitude, yaw, pitch, radius, distance, wander );
    }
	
	public SteerWander3(float minimum, float maximum, float yaw, float pitch, float radius, float distance, float wander)
	{
	    super( minimum, maximum );
	    
		this.yaw = yaw;
		this.pitch = pitch;
		this.radius = radius;
		this.distance = distance;
		this.wander = wander;
	}
	
	@Override
	public float getForce( float elapsed, SteerSubject<Vec3> subject, Vec3 out )
	{
		yaw += ( SteerMath.randomFloat( wander ) - (wander * 0.5f) ) * elapsed;
		pitch += ( SteerMath.randomFloat( wander ) - (wander * 0.5f) ) * elapsed;
		
		out.angle( yaw, pitch, radius );
		out.addsi( subject.getDirection(), distance );
		
		return forceFromVector( this, out );
	}

	@Override
	public boolean isShared()
	{
		return false;
	}

	@Override
	public Steer<Vec3> clone()
	{
		return new SteerWander3( minimum, maximum, yaw, pitch, radius, distance, wander );
	}

}
