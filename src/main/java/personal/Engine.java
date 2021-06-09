package personal;

public interface Engine {
    public void createCard();
    public User login(String numberInput, String PINInput);
    public void getInfo(User user);

}
