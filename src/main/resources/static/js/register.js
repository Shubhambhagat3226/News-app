async function handleSubmit(event) {
    event.preventDefault();

    const emailId = document.getElementById('emailId').value;
    const name = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    const userData= {emailId, name, password};

    const baseUrl = 'http://localhost:8031/register'
    try {
        const response = await fetch(baseUrl, {
            method: 'POST',
            headers: {  
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userData),
        });

        if (response.ok) {
            alert('Register successfully');
        }

    } catch (err) {
        console.log("err: ", err);
        alert('Error occure while calling the api')
    }
};