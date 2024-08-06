using UnityEngine;

public class CloudController : BaseController
{
    [Header("Movement Properties")]
    public float minHorizontal;
    public float maxHorizontal;
    public float minOffscreenVertical;
    public float maxOffscreenVertical;

    [Range(5.0f, 10.0f)]
    public float minVerticalSpeed;
    [Range(5.0f, 10.0f)]
    public float maxVerticalSpeed;
    [Range(-2.0f, 2.0f)]
    public float minHorizontalSpeed;
    [Range(-2.0f, 2.0f)]
    public float maxHorizontalSpeed;

    public float horizontalSpeed;


    public override void ResetGameObject()
    {
        var randomXPostion = Random.Range(minHorizontal, maxHorizontal);
        var randomYPostion = Random.Range(minOffscreenVertical, maxOffscreenVertical);
        horizontalSpeed = Random.Range(minHorizontalSpeed, maxHorizontalSpeed);
        verticalSpeed = Random.Range(minVerticalSpeed,maxVerticalSpeed);
        transform.position = new Vector3(randomXPostion, randomYPostion, 0.0f);
    }

    public override void Move()
    {
        transform.position += new Vector3(-horizontalSpeed * Time.deltaTime, -verticalSpeed * Time.deltaTime, 0.0f);
    }

    public override void CheckBounds()
    {
        if (transform.position.y <= boundary.min)
        {
            ResetGameObject();
        }
    }
}