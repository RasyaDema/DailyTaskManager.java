import java.util.*;

// Node class for Linked List
class TaskNode {
    String task;
    TaskNode next;

    public TaskNode(String task) {
        this.task = task;
        this.next = null;
    }
}

public class DailyTaskManager {
    private static List<String> tasks = new ArrayList<>(Arrays.asList("Periksa email", "Hadiri kuliah", "Latihan", "Baca buku", "Kerjakan tugas"));
    private static Stack<String> completedTasks = new Stack<>();
    private static Stack<Integer> completedIndexes = new Stack<>();
    private static TaskNode head = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("\n--- Daily Task Manager ---");
            System.out.println("1. Lihat daftar tugas");
            System.out.println("2. Perbarui atau Tambah tugas baru");
            System.out.println("3. Tandai tugas sebagai selesai");
            System.out.println("4. Batalkan penyelesaian tugas");
            System.out.println("5. Tambah tugas ke daftar dinamis");
            System.out.println("6. Hapus tugas dari daftar dinamis");
            System.out.println("7. Lihat daftar tugas dinamis");
            System.out.println("8. Keluar");
            System.out.print("Pilih opsi: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: displayTasks(); break;
                case 2: updateOrAddTask(scanner); break;
                case 3: completeTask(scanner); break;
                case 4: undoTask(); break;
                case 5: addTask(scanner); break;
                case 6: removeTask(scanner); break;
                case 7: displayDynamicTasks(); break;
                case 8: System.out.println("Keluar..."); break;
                default: System.out.println("Pilihan tidak valid!");
            }
        } while (choice != 8);
    }
    
    private static void displayTasks() {
        System.out.println("\nDaftar Tugas Harian:");
        if (tasks.isEmpty()) {
            System.out.println("Tidak ada tugas.");
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
    
    private static void updateOrAddTask(Scanner scanner) {
        System.out.println("1. Perbarui tugas yang ada");
        System.out.println("2. Tambah tugas baru");
        System.out.print("Pilih opsi: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        
        if (option == 1) {
            displayTasks();
            System.out.print("Masukkan nomor tugas yang ingin diperbarui: ");
            int index = scanner.nextInt() - 1;
            scanner.nextLine();
            if (index >= 0 && index < tasks.size()) {
                System.out.print("Masukkan tugas baru: ");
                tasks.set(index, scanner.nextLine());
                System.out.println("Tugas diperbarui!");
            } else {
                System.out.println("Nomor tugas tidak valid!");
            }
        } else if (option == 2) {
            System.out.print("Masukkan tugas baru: ");
            tasks.add(scanner.nextLine());
            System.out.println("Tugas berhasil ditambahkan!");
        } else {
            System.out.println("Pilihan tidak valid!");
        }
    }
    
    private static void completeTask(Scanner scanner) {
        displayTasks();
        System.out.print("Masukkan nomor tugas yang selesai: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        if (index >= 0 && index < tasks.size()) {
            completedTasks.push(tasks.get(index));
            completedIndexes.push(index);
            tasks.remove(index);
            System.out.println("Tugas telah diselesaikan dan dihapus dari daftar.");
        } else {
            System.out.println("Nomor tugas tidak valid!");
        }
    }
    
    private static void undoTask() {
        if (!completedTasks.isEmpty() && !completedIndexes.isEmpty()) {
            String undoneTask = completedTasks.pop();
            int index = completedIndexes.pop();
            tasks.add(index, undoneTask);
            System.out.println("Penyelesaian tugas \"" + undoneTask + "\" telah dibatalkan dan dikembalikan ke daftar.");
        } else {
            System.out.println("Tidak ada tugas yang dapat dibatalkan.");
        }
    }
    
    private static void addTask(Scanner scanner) {
        System.out.print("Masukkan tugas baru: ");
        String newTask = scanner.nextLine();
        TaskNode newNode = new TaskNode(newTask);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        System.out.println("Tugas ditambahkan ke daftar dinamis.");
    }
    
    private static void removeTask(Scanner scanner) {
        System.out.print("Masukkan tugas yang ingin dihapus: ");
        String taskToRemove = scanner.nextLine();
        
        if (head == null) {
            System.out.println("Daftar tugas kosong.");
            return;
        }
        
        if (head.task.equals(taskToRemove)) {
            head = head.next;
            System.out.println("Tugas berhasil dihapus.");
            return;
        }
        
        TaskNode temp = head;
        while (temp.next != null && !temp.next.task.equals(taskToRemove)) {
            temp = temp.next;
        }
        
        if (temp.next == null) {
            System.out.println("Tugas tidak ditemukan.");
        } else {
            temp.next = temp.next.next;
            System.out.println("Tugas berhasil dihapus.");
        }
    }
    
    private static void displayDynamicTasks() {
        System.out.println("\nDaftar Tugas Dinamis:");
        TaskNode temp = head;
        int count = 1;
        while (temp != null) {
            System.out.println(count + ". " + temp.task);
            temp = temp.next;
            count++;
        }
        if (count == 1) {
            System.out.println("Tidak ada tugas dalam daftar.");
        }
    }
}