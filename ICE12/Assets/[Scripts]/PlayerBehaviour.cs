using UnityEngine;
using UnityEngine.SceneManagement;

public class PlayerBehaviour : BaseController
{
    public float horizontalSpeed;
    public float verticalPosition;

    public AudioSource yaySound;
    public AudioSource thunderSound;

    public GameController gameController;

    public override void Start()
    {
        gameController = GameObject.Find("GameController").GetComponent<GameController>();

        transform.position = new Vector3(0.0f, verticalPosition, 0.0f);
    }

    public override void Move()
    {
        if(Input.touchCount > 0)
        {
            // gets input touches from screen space in pixles
            var x = Input.touches[0].position.x;

            var horizontalPosition = Camera.main.ScreenToWorldPoint(new Vector3(x, 0.0f, 0.0f)).x;

            transform.position = new Vector3(horizontalPosition, verticalPosition, 0.0f);
        }
    }

    public override void CheckBounds()
    {
        if(transform.position.x <= boundary.min)
        {
            transform.position = new Vector3(boundary.min, verticalPosition, 0.0f);
        }
        else if(transform.position.x >= boundary.max) 
        {
            transform.position = new Vector3(boundary.max, verticalPosition, 0.0f);
        }
    }

    public override void ResetGameObject()
    {
        //throw new System.NotImplementedException();
    }

    private void OnTriggerEnter2D(Collider2D other)
    {
        if(other.gameObject.CompareTag("Island"))
        {
            yaySound.Play();
            gameController.AddScore(100);
        } 
        else if(other.gameObject.CompareTag("Cloud"))
        {
            thunderSound.Play();
            gameController.LoseLife();

            if(gameController.GetLives() < 1)
            {
                SceneManager.LoadScene("End");
            }
        }
    }
}
