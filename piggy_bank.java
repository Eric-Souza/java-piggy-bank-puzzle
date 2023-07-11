import java.util.ArrayList;
import java.util.Scanner;

// Abstract class representing a generic coin
abstract class Coin {
    protected String country;
    protected double value;

    public Coin(String country, double value) {
        this.country = country;
        this.value = value;
    }

    public abstract double convertToBrazilianReal();
}

// Child class representing a dollar coin
class Dollar extends Coin {
    public Dollar() {
        super("United States", 1.0);
    }

    public double convertToBrazilianReal() {
        // Considering 1 dollar as 5 reais
        return value * 5.0; 
    }
}

// Child class representing an euro coin
class Euro extends Coin {
    public Euro() {
        super("Europe", 1.0);
    }

    public double convertToBrazilianReal() {
        // Considering 1 euro as 6 reais
        return value * 6.0; 
    }
}

// Child class representing a real coin
class Real extends Coin {
    public Real(double value) {
        super("Brazil", value);
    }

    public double convertToBrazilianReal() {
        return value;
    }
}

class PiggyBank {
    public ArrayList<Coin> coins;

    public PiggyBank() {
        coins = new ArrayList<>();
    }

    public void addCoin(Coin coin) {
        coins.add(coin);
        System.out.println("Coin added to piggy bank.");
    }

    public void removeCoin(Coin coin) {
        if (coins.remove(coin)) {
            System.out.println("Coin removed from piggy bank.");
        } else {
            System.out.println("Selected coin is not in piggy bank");
        }
    }

    public void listCoins() {
        System.out.println("Coins in piggy bank:");
        
        for (Coin coin : coins) {
            System.out.println("- " + coin.country + ": " + coin.value);
        }
    }

    public double calculateTotalInReal() {
        double total = 0.0;
        
        for (Coin coin : coins) {
            total += coin.convertToBrazilianReal();
        }
        
        return total;
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PiggyBank piggy_bank = new PiggyBank();

        int option;
        
        do {
            // Renderiza o menu de interacao
            System.out.println("\n--- Menu ---\n");
            System.out.println("1. Add coin");
            System.out.println("2. Remove coin");
            System.out.println("3. List coins");
            System.out.println("4. Total in brazilian reais");
            System.out.println("0. Leave\n");
            System.out.print("Please select an option: ");
            
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Inform type of coin (1 - Dollar, 2 - Euro, 3 - Real): ");
                    int coinType = scanner.nextInt();
                    
                    Coin coin;
                    switch (coinType) {
                        case 1:
                            coin = new Dollar();
                            break;
                            
                        case 2:
                            coin = new Euro();
                            break;
                            
                        case 3:
                            System.out.print("Inform the value in reais: ");
                            double valueReal = scanner.nextDouble();
                            
                            coin = new Real(valueReal);
                            break;
                            
                        default:
                            System.out.println("Invalid option.");
                            continue;
                    }
                    
                    piggy_bank.addCoin(coin);
                    break;
                    
                case 2:
                    piggy_bank.listCoins();
                    
                    System.out.print("Inform the country of the coin to be removed: ");
                    String countryCoin = scanner.next();
                    
                    System.out.print("Inform the value of the coin to be removed: ");
                    double valueCoin = scanner.nextDouble();
                    
                    Coin coinToRemove = null;
                    
                    for (Coin m : piggy_bank.coins) {
                        if (m.country.equalsIgnoreCase(countryCoin) && m.value == valueCoin) {
                            coinToRemove = m;
                            break;
                        }
                    }
                    
                    if (coinToRemove != null) {
                        piggy_bank.removeCoin(coinToRemove);
                    } else {
                        System.out.println("The specified coin is not in the piggy bank.");
                    }
                    
                    break;
                    
                case 3:
                    piggy_bank.listCoins();
                    break;
                    
                case 4:
                    double totalInReais = piggy_bank.calculateTotalInReal();
                    System.out.println("Total in reais: R$" + totalInReais);
                    break;
                    
                case 0:
                    System.out.println("Shutting down system...");
                    break;
                    
                default:
                    System.out.println("Invalid option.");
            }
        } while (option != 0);

        scanner.close();
    }
}
