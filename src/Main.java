import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Animal> pets = new ArrayList<>();
        ArrayList<Playable> playables = new ArrayList<>();
        ArrayList<String> logs = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== ペット管理アプリ（ログ付き） ===");

        while (true) {
            System.out.println("\n操作を選んでください:");
            System.out.println("1: 登録  2: 一覧表示  3: 削除  4: 全員実行  5: 種類別鳴き声  6: 名前検索して遊ばせる  7: ログ表示  8: 終了");
            System.out.print("番号を入力：");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1": // 登録
                    System.out.print("動物の種類を入力（dog/cat/bird）：");
                    String type = scanner.nextLine().trim().toLowerCase();
                    System.out.print("名前を入力：");
                    String name = scanner.nextLine().trim();

                    Animal pet = null;
                    switch (type) {
                        case "dog":
                            pet = new Dog(name);
                            playables.add((Playable) pet);
                            break;
                        case "cat":
                            pet = new Cat(name);
                            playables.add((Playable) pet);
                        case "Bird":
                            pet = new Bird(name);
                            break;
                        default:
                            System.out.println("未対応の動物です。");
                            continue;
                    }

                    pets.add(pet);
                    System.out.println(name + " を登録しました！");
                    logs.add(name + " を登録しました。");
                    break;

                case "2": // 一覧表示
                    System.out.println("\n=== ペット一覧 ===");
                    if (pets.isEmpty()) System.out.println("登録されているペットはいません。");
                    else {
                        for (int i = 0; i < pets.size(); i++) {
                            System.out.println((i + 1) + ": " + pets.get(i).name + " (" + pets.get(i).getClass().getSimpleName() + ")");
                        }
                    }
                    break;

                case "3": // 削除
                    System.out.print("削除するペットの番号を入力：");
                    try {
                        int index = Integer.parseInt(scanner.nextLine()) - 1;
                        if (index >= 0 && index < pets.size()) {
                            Animal removed = pets.remove(index);
                            if (removed instanceof Playable) playables.remove(removed);
                            System.out.println(removed.name + " を削除しました。");
                            logs.add(removed.name + " を削除しました。");
                        } else System.out.println("無効な番号です。");
                    } catch (NumberFormatException e) {
                        System.out.println("数字を入力してください。");
                    }
                    break;

                case "4": // 全員実行
                    System.out.println("\n=== 自己紹介 ===");
                    for (Animal a : pets) {
                        a.introduce();
                        logs.add(a.name + " が自己紹介しました。");
                    }

                    System.out.println("\n=== 鳴き声 ===");
                    for (Animal a : pets) {
                        a.makeSound();
                        logs.add(a.name + " が鳴きました。");
                    }

                    System.out.println("\n=== 遊びタイム ===");
                    for (Playable p : playables) {
                        p.play();
                        logs.add(((Animal)p).name + " が遊びました。");
                    }
                    break;

                case "5": // 種類別鳴き声
                    System.out.print("鳴かせたい動物の種類を入力（dog/cat/bird）：");
                    String targetType = scanner.nextLine().trim().toLowerCase();
                    System.out.println("\n=== " + targetType + " の鳴き声 ===");
                    for (Animal a : pets) {
                        if (a.getClass().getSimpleName().toLowerCase().equals(targetType)) {
                            a.makeSound();
                            logs.add(a.name + " が鳴きました。");
                        }
                    }
                    break;

                case "6": // 名前検索して遊ばせる
                    System.out.print("遊ばせたいペットの名前を入力：");
                    String targetName = scanner.nextLine().trim();
                    boolean found = false;
                    for (Playable p : playables) {
                        if (((Animal)p).name.equals(targetName)) {
                            p.play();
                            logs.add(((Animal)p).name + " が遊びました。");
                            found = true;
                        }
                    }
                    if (!found) System.out.println(targetName + " は遊べるペットにいません。");
                    break;

                case "7": // ログ表示
                    System.out.println("\n=== 行動ログ ===");
                    if (logs.isEmpty()) System.out.println("まだ記録はありません。");
                    else {
                        for (String log : logs) {
                            System.out.println(log);
                        }
                    }
                    break;

                case "8": // 終了
                    System.out.println("終了します。");
                    scanner.close();
                    return;

                default:
                    System.out.println("無効な操作です。");
            }
        }
    }
}
