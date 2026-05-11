// Resaltar fila activa en tablas al hacer click
document.querySelectorAll('table tbody tr').forEach(row => {
    row.addEventListener('click', function (e) {
        if (e.target.tagName === 'BUTTON' || e.target.tagName === 'A') return;
        this.classList.toggle('selected');
    });
});

// Cerrar alertas automáticamente
document.querySelectorAll('.alert').forEach(alert => {
    setTimeout(() => { alert.style.display = 'none'; }, 4000);
});
