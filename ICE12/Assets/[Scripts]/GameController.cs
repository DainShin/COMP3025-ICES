using TMPro;
using UnityEngine;

[System.Serializable]
public class GameController : MonoBehaviour
{
    [Header("Scorebaord")]
    public TMP_Text livesLabel;
    public TMP_Text scoreLabel;

    private int m_score;
    private int m_lives;
    
    void Start()
    {
        SetScoreLabel(0);
        SetLiveLabel(5);
    }

    public void AddScore(int score)
    {
        m_score += score;
        SetScoreLabel(m_score);
    }

    public void LoseLife()
    {
        m_lives --;
        SetLiveLabel(m_lives);
    }
    
    public void SetLiveLabel(int lives)
    {
        livesLabel.text = "Live: " + lives;
        m_lives = lives;
    }

    public int GetLives()
    {
        return m_lives;
    }

    public void SetScoreLabel(int score)
    {
        scoreLabel.text = "Score: " + score;
        m_score = score;
    }

    public int GetScore()
    {
        return m_score;
    }


}
