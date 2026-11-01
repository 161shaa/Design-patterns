/**
Implemented a Customer Support Escalation System using two design patterns:

Builder Pattern: Creates Complaint objects with multiple attributes and validates the data before object creation.

Chain of Responsibility: Routes customer complaints through different handlers (Chatbot, Support Agent, Technical Team, and Manager) based on complaint severity.

This implementation demonstrates clean object creation, separation of responsibilities, and flexible request handling.
**/




class Complaint {

    private final String complaint;
    private final int complaintID;
    private final int severity;
    private final int imagesIfAny;
    private final String name;
    private final String address;
    private final String phoneNumber;

    private Complaint(Builder builder) {
        this.complaint = builder.complaint;
        this.complaintID = builder.complaintID;
        this.severity = builder.severity;
        this.imagesIfAny = builder.imagesIfAny;
        this.name = builder.name;
        this.address = builder.address;
        this.phoneNumber = builder.phoneNumber;
    }

  
    public String getComplaint() {
        return complaint;
    }

    public int getSeverity() {
        return severity;
    }
  

   
    static class Builder {

        private String complaint;
        private int complaintID;
        private int severity;
        private int imagesIfAny;
        private String name;
        private String address;
        private String phoneNumber;

        public Builder complaint(String complaint) {
            this.complaint = complaint;
            return this;
        }

        public Builder complaintID(int complaintID) {
            this.complaintID = complaintID;
            return this;
        }

        public Builder severity(int severity) {
            this.severity = severity;
            return this;
        }

        public Builder imagesIfAny(int imagesIfAny) {
            this.imagesIfAny = imagesIfAny;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Complaint build() {return new Complaint(this);
      }
    }
}


// Abstract Handler

abstract class ComplaintHandler {

    protected ComplaintHandler handler;

    public void setHandler(ComplaintHandler handler) {
        this.handler = handler;
    }

    public abstract void handle(Complaint complaint);
}


// Chatbot Handler

class ChatbotHandler extends ComplaintHandler {

    public void handle(Complaint complaint) {

        if (complaint.getSeverity() == 1) {

            System.out.println("Chatbot resolved: "
                    + complaint.getComplaint());

        } else {

            System.out.println(
                "Chatbot cannot resolve. Escalating..."
            );

            if (handler != null) {
                handler.handle(complaint);
            }
        }
    }
}


// Support Agent Handler

class SupportAgentHandler extends ComplaintHandler {

    public void handle(Complaint complaint) {

        if (complaint.getSeverity() == 2) {

            System.out.println("Support Agent resolved: "
                    + complaint.getComplaint());

        } else {

            System.out.println(
                "Escalating to technical team..."
            );

            if (handler != null) {
                handler.handle(complaint);
            }
        }
    }
}


// Technical Team Handler

class TechnicalTeamHandler extends ComplaintHandler {

    public void handle(Complaint complaint) {

        if (complaint.getSeverity() == 3) {

            System.out.println("Technical Team resolved: "
                    + complaint.getComplaint());

        } else {

            System.out.println(
                "Escalating to manager..."
            );

            if (handler != null) {
                handler.handle(complaint);
            }
        }
    }
}


// Manager Handler

class ManagerHandler extends ComplaintHandler {

    public void handle(Complaint complaint) {

        System.out.println(
            "Manager handling critical complaint: "
            + complaint.getComplaint()
        );
    }
}


// Main Class

public class Main {

    public static void main(String[] args) {

        // Create Complaint using Builder Pattern

        Complaint complaint_by_rahul = new Complaint.Builder()
                .name("Rahul")
                .address("Hyderabad")
                .phoneNumber("999-999-999")
                .severity(4)
                .complaint("I need refund")
                .build();


        // Create Handlers

        ComplaintHandler chatbot = new ChatbotHandler();
        ComplaintHandler supportTeam = new SupportAgentHandler();
        ComplaintHandler techTeam = new TechnicalTeamHandler();
        ComplaintHandler manager = new ManagerHandler();


        // Build Chain

        chatbot.setHandler(supportTeam);
        supportTeam.setHandler(techTeam);
        techTeam.setHandler(manager);


        // Start Chain

        chatbot.handle(complaint_by_rahul);
    }
}
