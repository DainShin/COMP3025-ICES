using UnityEngine;

public abstract class BaseController : MonoBehaviour
{
    // common properties
    public float verticalSpeed;
    public Boundary boundary;

    // Abstract methods for subclasses to implement
    public abstract void ResetGameObject();
    public abstract void Move();

    public virtual void CheckBounds()
    {
        if (transform.position.y <= boundary.min)
        {
            ResetGameObject();
        }
    }

    public virtual void Start()
    {
        ResetGameObject();
    }

    public virtual void Update()
    {
        Move();
        CheckBounds();
    }
}
