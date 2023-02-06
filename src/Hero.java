import java.util.Random;
public class Hero {
    private String name;
    private int hitPoints;

    public Hero(String n)
    {
        name = n;
        hitPoints = 100;
    }

    public String getName(){
        return name;
    }
    public int getHitPoints(){
        return hitPoints;
    }
    public String toString(){
        return "Hero{name='" + name + "', hitPoints=" + hitPoints + "}";
    }
    public void attack(Hero opponent){
        Random rand = new Random();
        float rng = rand.nextFloat();

        if (rng < 0.5){
            opponent.hitPoints = opponent.hitPoints - 10;
        }
        else
            this.hitPoints = this.hitPoints - 10;
    }
    public void senzuBean(){
        this.hitPoints = 100;
    }

    private void fightUntilTheDeathHelper(Hero opponent){
        while (this.hitPoints > 0 && opponent.hitPoints > 0){
            this.attack(opponent);
        }
    }

    public String fightUntilTheDeath(Hero opponent){
        this.senzuBean();
        opponent.senzuBean();
        this.fightUntilTheDeathHelper(opponent);
        return this.getName() + ": " + this.getHitPoints() + "\t" + opponent.getName() + ": " + opponent.getHitPoints();
    }

    private int[] nFightsToTheDeathHelper(Hero opponent, int n) {
        int[] winLoss = new int[2];

        while(n>0) {
            while (this.hitPoints > 0 || opponent.hitPoints > 0) {
                this.attack(opponent);
            }
            if (this.hitPoints != 0){
                winLoss[0] = winLoss[0] + 1;
            }
            else
                winLoss[1] = winLoss[1] + 1;
            n--;
            this.senzuBean();
            opponent.senzuBean();
        }
        return winLoss;
    }

    public String nFightsToTheDeath(Hero opponent, int n){
        int [] winLoss = nFightsToTheDeathHelper(opponent, n);
        int heroWins = winLoss[0];
        int opponentWins = winLoss[1];

        String winMessage = "";

        if (heroWins > opponentWins)
        {
            winMessage = this.name + "wins!";
        }
        else if (opponentWins > heroWins)
        {
            winMessage = opponent.name + "wins!";
        }
        else
            winMessage = "OMG! It was actually a draw!";

        return this.name + ": " + heroWins + "wins \n" + opponent.name + ": " + opponentWins + "wins \n" + winMessage;
    }

    public void dramaticFightToTheDeath(Hero opponent) {
        while (this.hitPoints > 0 || opponent.hitPoints > 0) {
            this.attack(opponent);
            System.out.println(this.getName() + ": " + this.getHitPoints() + "\t" + opponent.getName() + ": " + opponent.getHitPoints() + "\n");
        }
        if (this.hitPoints != 0){
            System.out.println(this.name + " wins!");
        }
        else
            System.out.println(opponent.name + " wins!");
    }
}
