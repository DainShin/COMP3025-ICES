using UnityEngine;

public class OceanController : BaseController
{


    public override void ResetGameObject()
    {
        transform.position = new Vector3(0.0f, boundary.max, 0.0f);
    }

    public override void Move()
    {
        transform.position += new Vector3(0.0f,-verticalSpeed * Time.deltaTime, 0.0f);
    }

}
