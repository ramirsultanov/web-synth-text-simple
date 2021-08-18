package searchiarium.ct.com.websynthsimpletext.validators;

import org.springframework.stereotype.Component;

@Component
public class SimpleBookNameValidator implements AbstractValidator<String> {
    @Override
    public boolean validate(String name) {
        return name.matches("[a-zA-Z0-9]{8,240}");
    }
}
