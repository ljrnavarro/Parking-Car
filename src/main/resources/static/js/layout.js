function setActiveMenu() {
  const page = window.location.pathname.split('/').pop();

  document.querySelectorAll('.sidebar a').forEach(link => {
    if (link.getAttribute('href') === page) {
      link.classList.add('active');
    }
  });
}
