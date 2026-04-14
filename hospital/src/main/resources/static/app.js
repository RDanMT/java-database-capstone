let currentRole = 'ADMIN';

// Maneja cual de las 3 opciones seleccionamos (Para capturar P.13, P.14, P.15)
function setRole(role) {
    currentRole = role;
    const title = document.getElementById('role-title');
    const buttons = document.querySelectorAll('.role-selector button');

    buttons.forEach(btn => btn.classList.remove('selected'));
    event.target.classList.add('selected');

    if (role === 'ADMIN') title.innerText = 'Acceso Administradores';
    if (role === 'DOCTOR') title.innerText = 'Acceso Personal Médico';
    if (role === 'PATIENT') title.innerText = 'Portal Pacientes';
}

// Al pulsar el botón entrar (Simulación de Fetch para ir al siguiente panel)
document.getElementById('login-form').addEventListener('submit', (e) => {
    e.preventDefault();
    // Magia para desaparecer Login y aparecer la vista correcta (Para capturar P.16, P.17, P.18)
    document.getElementById('login-view').classList.remove('active');

    if (currentRole === 'ADMIN') document.getElementById('admin-view').classList.add('active');
    if (currentRole === 'DOCTOR') document.getElementById('doctor-view').classList.add('active');
    if (currentRole === 'PATIENT') document.getElementById('patient-view').classList.add('active');
});

// Cerrar y retornar al login
function logout() {
    document.querySelectorAll('.view').forEach(view => view.classList.remove('active'));
    document.getElementById('login-view').classList.add('active');
    document.getElementById('login-form').reset();
}

// Selección inicial automática
document.addEventListener("DOMContentLoaded", () => {
    document.querySelector('.role-selector button').classList.add('selected');
});
