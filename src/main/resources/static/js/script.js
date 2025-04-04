async function handleSubmit(event) {
    event.preventDefault();

    const emailId = document.getElementById('emailId').value;
    const password = document.getElementById('password').value;

    const loginData= {emailId, password};

    // console.log('user data :', JSON.stringify(loginData));


    const baseUrl = 'http://localhost:8031/login'
    try {
        const response = await fetch(baseUrl, {
            method: 'POST',
            headers: {  
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(loginData),
        });

        const data = await response.json();

        if (data) {
            alert('Login successfully');
        }
        else {
            alert('Invalid credentials, please try again');
        }



    } catch (err) {
        console.log("err: ", err);
        alert('Error occure while calling the api')
    }
};