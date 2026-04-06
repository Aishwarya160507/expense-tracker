const BASE_URL = "http://localhost:8080/api";

let currentUser = "";
let currentPassword = "";

// REGISTER
async function register() {
    const email = document.getElementById("regEmail").value;
    const password = document.getElementById("regPassword").value;

    try {
        const res = await fetch(BASE_URL + "/auth/register", {
            method: "POST",
            headers: {"Content-Type":"application/json"},
            body: JSON.stringify({email, password})
        });

        const text = await res.text();
        alert(text);

    } catch (err) {
        alert("Error connecting to backend");
        console.error(err);
    }
}

// LOGIN
async function login() {
    const email = document.getElementById("loginEmail").value;
    const password = document.getElementById("loginPassword").value;

    try {
        const res = await fetch(BASE_URL + "/auth/login", {
            method: "POST",
            headers: {"Content-Type":"application/json"},
            body: JSON.stringify({email, password})
        });

        const user = await res.json();

        if (user && user.id) {
            currentUser = email;
            currentPassword = password;

            document.getElementById("app").style.display = "block";

            loadExpenses();
        } else {
            alert("Invalid login");
        }

    } catch (err) {
        alert("Login error");
        console.error(err);
    }
}

// ADD EXPENSE
async function addExpense() {
    try {
        await fetch(BASE_URL + "/expenses/" + currentUser, {
            method: "POST",
            headers: {"Content-Type":"application/json"},
            body: JSON.stringify({
                title: document.getElementById("title").value,
                amount: document.getElementById("amount").value,
                category: document.getElementById("category").value,
                date: document.getElementById("date").value,
                user: { password: currentPassword }
            })
        });

        loadExpenses();

    } catch (err) {
        console.error(err);
    }
}

// LOAD EXPENSES
async function loadExpenses() {
    const res = await fetch(BASE_URL + "/expenses/" + currentUser);
    const data = await res.json();

    const list = document.getElementById("list");
    list.innerHTML = "";

    data.forEach(exp => {
        const li = document.createElement("li");

        li.innerHTML = `
            ${exp.title} - ₹${exp.amount}
            <button class="delete-btn" onclick="deleteExpense(${exp.id})">❌</button>
        `;

        list.appendChild(li);
    });
}

// DELETE EXPENSE
async function deleteExpense(id) {
    await fetch(BASE_URL + "/expenses/" + id, {
        method: "DELETE"
    });

    loadExpenses();
}