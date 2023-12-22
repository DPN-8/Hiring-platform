package com.hiringplatform.Contest.Service.ControllerService;

import com.hiringplatform.Contest.model.Employee;
import com.hiringplatform.Contest.model.Guest;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssignGuestsToEmployees {
    public static List<EmployeeGuestPair> assignGuestsToEmployees(List<Employee> employees, List<Guest> guests) {
        List<EmployeeGuestPair> employeeGuestPairs = new ArrayList<>();

        Map<String, List<Employee>> stackToEmployees = new HashMap<>();
        for (Employee employee : employees) {
            String stack = employee.getExpertise();
            List<Employee> employeesWithStack = stackToEmployees.getOrDefault(stack, new ArrayList<>());
            employeesWithStack.add(employee);
            stackToEmployees.put(stack, employeesWithStack);
        }

        for (String stack : stackToEmployees.keySet()) {

            List<Employee> employeesWithStack = stackToEmployees.get(stack);
            List<Guest> guestsWithStack = guests.stream().filter(guest -> guest.getStack().equals(stack)).toList();

            int remainder = guestsWithStack.size() % employeesWithStack.size();

            int guestPosition = 0;
            for (int i = 0; i < (int) (guestsWithStack.size() / employeesWithStack.size()); i++) {
                int guestCount = guestsWithStack.size();
                for (Employee employee : employeesWithStack) {
                    System.out.println(stack + " : " + guestPosition);
                    Guest guest = guestsWithStack.get(guestPosition);
                    if(guestCount == 0) break;
                    if(guestsWithStack.size() < employeesWithStack.size()){
                        employeeGuestPairs.add(new EmployeeGuestPair(employee, guest));
                        guestCount--;
                        continue;
                    }
                    if (guest.getStack().equals(employee.getExpertise())) {
                        employeeGuestPairs.add(new EmployeeGuestPair(employee, guest));
                    }
                    if(remainder != 0) {
                        employeeGuestPairs.add(new EmployeeGuestPair(employee, guest));
                        remainder--;
                    }
                    System.out.println(employee.getEid() + " : " + guest.getUserId() + " : " + stack);
                    guestPosition++;
                }
            }
        }

        return employeeGuestPairs;
    }

    @Getter
    public static class EmployeeGuestPair {
        private Employee employee;
        private Guest guest;

        public EmployeeGuestPair(Employee employee, Guest guest) {
            this.employee = employee;
            this.guest = guest;
        }

    }
}
