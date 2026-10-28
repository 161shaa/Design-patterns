class SendEmail{
    private String to;
    private String cc;
    private String bcc;
    private String attachments;
    private int EmailIDNumber;
    private double IPPackageNumber;

      // Constructor: copies builder values into the email object
    private SendEmail(EmailBuilder builder) {
        this.to = builder.to;
        this.cc = builder.cc;
        this.bcc = builder.bcc;
        this.attachments = builder.attachments;
        this.EmailIDNumber = builder.EmailIDNumber;
        this.IPPackageNumber = builder.IPPackageNumber;
    }

    static class EmailBuilder{
    
    private String to;
    private String cc;
    private String bcc;
    private String attachments;
    private int EmailIDNumber;
    private double IPPackageNumber;


    public EmailBuilder to(String to ){
        this.to=to;
        return this;
    }

    public EmailBuilder cc(String cc){
        this.cc=cc;
        return this;
    }

    public EmailBuilder bcc(String bcc){
        this.bcc=bcc;
        return this;
    }

    public EmailBuilder EmailIDNumber(int EmailIDNumber){
        this.EmailIDNumber=EmailIDNumber;
        return this;
    }

    public EmailBuilder IPPackageNumber(double IPPackageNumber){
        this.IPPackageNumber=IPPackageNumber;
        return this;
    }

    public EmailBuilder attachments(String attachments){
        this.attachments=attachments;
        return this;
    }

    public SendEmail build(){
        return new SendEmail(this);
    }
    }

    public void display() {
        System.out.println("To: " + this.to);
        System.out.println("CC: " + this.cc);
        System.out.println("BCC: " + this.bcc);
        System.out.println("Attachments: " + this.attachments);
        System.out.println("Email ID: " + this.EmailIDNumber);
        System.out.println("IP Package Number: " + this.IPPackageNumber);
    }
    
}

public class Main {
    public static void main(String[] args) {

        SendEmail email_object_one = new SendEmail.EmailBuilder()
                                                                .EmailIDNumber(10987564)
                                                                .to("161shaa@gmail.com")
                                                                .cc("afroze@gmail.com")
                                                                .attachments("Builder_patter.pdf")
                                                                .build();

        email_object_one.display();
      
        System.out.println("-------------------------------------------------------------------");
      
        SendEmail email_object_two = new SendEmail.EmailBuilder()
                                                                .EmailIDNumber(1044553)
                                                                .to("Alicegmail.com")
                                                                .cc("bob@gmail.com")
                                                                .attachments("Builder_patter.pdf")
                                                                .build();

        email_object_two.display();

        
    }
}
