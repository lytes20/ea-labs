
@Component
public class PersonMessageListener2 {
    @JmsListener(destination = "testQueue")
    public void receiveMessage(final String personAsString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Person person = objectMapper.readValue(personAsString, Person.class);
            System.out.println("JMS receiver 2 received message:" + person.getFirstName() + " " + person.getLastName());
        } catch (IOException e) {
            System.out.println("JMS receiver 2: Cannot convert : " + personAsString + " to a Person object");
        }
    }
}