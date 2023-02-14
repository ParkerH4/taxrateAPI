function revealAdd() {
  document.getElementById("add").classList.remove("hidden");
  document.getElementById("search").classList.add("hidden");
  document.getElementById("searchTable").classList.add("hidden");
}
function revealSearch() {
  document.getElementById("search").classList.remove("hidden");
  document.getElementById("searchTable").classList.remove("hidden");
  document.getElementById("add").classList.add("hidden");
}
