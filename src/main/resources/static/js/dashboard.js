document.addEventListener('DOMContentLoaded', () => {
  document.getElementById('pageTitle').innerText = 'Dashboard';

  // Exemplo simples (pode ligar na API depois)
  document.getElementById('dashboardContent').innerHTML = `
    <div class="row g-3">
      <div class="col-md-3">
        <div class="card">
          <div class="card-body">
            <small>Total de Veículos</small>
            <h3>--</h3>
          </div>
        </div>
      </div>
    </div>
  `;
});
