document.addEventListener('DOMContentLoaded', () => {

  // Título da página
  const pageTitle = document.getElementById('pageTitle');
  if (pageTitle) {
    pageTitle.innerText = 'Veículos';
  }

  // Definição das colunas
  const columnDefs = [
    { headerName: 'ID', field: 'id', width: 80 },
    { headerName: 'Placa', field: 'placa' },
    { headerName: 'Proprietário', field: 'nomeProprietario' },
   {
     headerName: 'Tipo',
     field: 'tipo',
     width: 120,
     cellRenderer: p => {
       const map = {
         LEVE: 'success',
         MEDIO: 'warning',
         PESADO: 'danger'
       };

       const color = map[p.value] || 'secondary';

       return `
         <span class="badge bg-${color}">
           ${p.value}
         </span>
       `;
     }
   },
    {
      headerName: 'Ações',
      width: 130,
      sortable: false,
      filter: false,
      cellRenderer: params => `
        <div class="d-flex gap-1">
          <button class="btn btn-sm btn-outline-primary"
                  title="Editar"
                  onclick="editarVeiculo(${params.data.id})">
            ✏️
          </button>

          <button class="btn btn-sm btn-outline-danger"
                  title="Excluir"
                  onclick="excluirVeiculo(${params.data.id})">
            🗑️
          </button>
        </div>
      `
    }
  ];

  // Opções do grid
  const gridOptions = {
    theme: 'legacy',

    columnDefs,
    rowData: [],

    pagination: true,
    paginationPageSize: 10,
    paginationPageSizeSelector: [10, 20, 50],

    rowHeight: 44,
    headerHeight: 46,

    animateRows: true,
    suppressCellFocus: true,

    defaultColDef: {
      sortable: true,
      filter: true,
      floatingFilter: true,
      resizable: true,
      minWidth: 120
    },

    onGridReady: params => {
      carregarVeiculos(params.api);
    }
  };

  // Inicialização do grid
  const gridDiv = document.getElementById('veiculosGrid');
  if (!gridDiv) {
    console.error('Elemento #veiculosGrid não encontrado');
    return;
  }

  agGrid.createGrid(gridDiv, gridOptions);
});

// =======================
// FUNÇÕES
// =======================

function carregarVeiculos(gridApi) {
  console.log('🚗 Carregando veículos...');

  api.get('/veiculos')
    .then(response => {
      console.log('✅ Veículos recebidos:', response.data);

      // 🔥 AQUI ESTÁ O ERRO QUE QUEBRAVA TUDO
      const veiculos = response.data.data; // <-- SOMENTE O ARRAY

      if (!Array.isArray(veiculos)) {
        console.error('❌ Dados inválidos para o grid:', veiculos);
        return;
      }

      gridApi.setGridOption('rowData', veiculos);
    })
    .catch(error => {
      console.error('❌ Erro ao carregar veículos', error);
      gridApi.setGridOption('rowData', []);
    });
}

function editarVeiculo(id) {
  console.log('Editar veículo', id);
  // futuramente: abrir modal
}

function excluirVeiculo(id) {
  if (!confirm('Deseja realmente excluir este veículo?')) return;

  api.delete(`/veiculos/${id}`)
    .then(() => {
      alert('Veículo excluído com sucesso');
      location.reload();
    });
}

function salvarVeiculo() {

  const payload = {
    placa: document.getElementById('placa').value.trim(),
    nomeProprietario: document.getElementById('nomeProprietario').value.trim(),
    tipo: document.getElementById('tipo').value
  };

  if (!payload.placa || !payload.nomeProprietario || !payload.tipo) {
    alert('Preencha todos os campos');
    return;
  }

  console.log('📤 Enviando veículo:', payload);

  api.post('/veiculos', payload)
    .then(() => {
      alert('✅ Veículo cadastrado com sucesso');

      // Fecha modal
      const modal = bootstrap.Modal.getInstance(
        document.getElementById('novoVeiculoModal')
      );
      modal.hide();

      // Limpa form
      document.getElementById('novoVeiculoForm').reset();

      // Recarrega grid
      location.reload();
    })
    .catch(err => {
      console.error('❌ Erro ao cadastrar veículo', err);
      alert('Erro ao cadastrar veículo');
    });
}
