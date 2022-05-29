package singleton;

public class SingleTonService {

    //1. static 영역에 객체 instance하나를 생성해 올려둔다.
    //2. 이 객체 인스턴스가 필요하면 오직 getInstance메소드를 통해서만 조회할 수 있다.
    //3. 딱 1개의 객체 인스턴스만 존재하므로 생성자를 private로 막아 외부에서 new 키워드로 객체 인스턴스가 생성되는 것을 막는다.

    private static final SingleTonService instance = new SingleTonService();

    public static SingleTonService getInstance() {
        return instance;
    }

    private SingleTonService () {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
