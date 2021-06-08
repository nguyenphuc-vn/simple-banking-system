package personal;

import org.junit.jupiter.api.*;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BankAppTest {
    private static final Logger logger = Logger.getLogger(BankAppTest.class.getName());
   // Engine engine ;
    private EngineSystem engineSystem;
    @BeforeAll
    public void setUp(){
        //engine = new EngineSystem();
        engineSystem = new EngineSystem();
    }

    @Test
    @DisplayName("Check if a card has been created")
    public void invokeCreateCard_shouldNewCardAndUser(){
        engineSystem.createCard();
        assertNotNull(engineSystem.getAccounts().entrySet());
        assertEquals(false,engineSystem.getAccounts().isEmpty());
    }

    @Test
    @DisplayName("1.Test login function")
    public void givenMockString_shouldReturnNull(){
        User user = engineSystem.login("3232", "323");
        assertNull(user);
    }
    @Nested
    class BankAppTestNest{
        private String number ;
        private String PIN;
        @BeforeEach
        public void setUp(){
            engineSystem.createCard();
            Map.Entry<Card,User> entry = engineSystem.getAccounts().entrySet().iterator().next();
            number = entry.getKey().getCardNumber();
            PIN = entry.getKey().getCardPIN();
        }
        @Test
        @DisplayName("2.Test login function")
        public void givenTheExactSameNumber_shouldReturnUser(){
            User user = engineSystem.login(number, PIN);
            assertNotNull(user);
        }
    }

}