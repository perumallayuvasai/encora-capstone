import React from "react";
import RegisterForm from "../../components/registerForm";
import { useNavigate } from "react-router";
import { useEffect } from "react";
import { useUser } from "../../provider/userProvider";

function RegisterPage() {
    const navigate = useNavigate();
    const userContext = useUser();
    useEffect(() => {
        if (userContext.user) {
            navigate("/");
        }
    });
    return (
        <div className="register-page flex-1 grid grid-cols-2">
            <RegisterForm />
        </div>
    );
}

export default RegisterPage;
