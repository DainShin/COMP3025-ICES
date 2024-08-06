using UnityEngine;

public class IslandController : BaseController
{
    public float maxHorizontal;
    public float minHorizontal;

   
    public override void ResetGameObject()
    {
        var randomXPosition = Random.Range(minHorizontal, maxHorizontal);
        transform.position = new Vector3(randomXPosition, boundary.max, 0.0f);
    }

    public override void Move()
    {
        transform.position += new Vector3(0.0f, -verticalSpeed * Time.deltaTime, 0.0f);
    }
}