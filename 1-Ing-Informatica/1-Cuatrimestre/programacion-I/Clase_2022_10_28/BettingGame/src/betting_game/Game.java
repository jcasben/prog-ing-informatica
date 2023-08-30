package betting_game;

public class Game {

    private void Start(){

        Functions fncts = new Functions();
        fncts.game();

    }
    public static void main(String[] args) {

        (new Game()).Start();
    }
}
