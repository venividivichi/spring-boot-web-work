$(document).ready(function() {
    FormValidation.Validator.password = {
        validate: function(validator, $field, options) {
            var value = $field.val();
            if (value === '') {
                return true;
            }

            if (value.length < 8) {
                return false;
            }
            if (value === value.toLowerCase()) {
                return false;
            }
            if (value === value.toUpperCase()) {
                return false;
            }
            if (value.search(/[0-9]/) < 0) {
                return false;
            }

            return true;
        }
    };

    $('#CustForm').formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                validators: {
                    notEmpty: {
                        message: 'The password is required and cannot be empty'
                    },
                    name: {
                        message: 'The password is not valid'
                    }
                }
            }
        }
        
    });
});