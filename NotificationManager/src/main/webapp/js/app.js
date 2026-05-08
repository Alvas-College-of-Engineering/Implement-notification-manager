document.addEventListener("DOMContentLoaded",()=>{
  const overlay=document.getElementById("modalOverlay");
  const open=()=>overlay.classList.add("open");
  const close=()=>overlay.classList.remove("open");
  document.getElementById("openModalBtn")?.addEventListener("click",open);
  document.getElementById("closeModalBtn")?.addEventListener("click",close);
  document.getElementById("cancelBtn")?.addEventListener("click",close);
  overlay?.addEventListener("click",e=>{if(e.target===overlay)close();});
  const toggle=document.getElementById("menuToggle");
  const sidebar=document.querySelector(".sidebar");
  toggle?.addEventListener("click",()=>sidebar?.classList.toggle("open"));
  document.addEventListener("click",e=>{
    if(window.innerWidth<=768&&sidebar?.classList.contains("open")&&!sidebar.contains(e.target)&&!toggle.contains(e.target))
      sidebar.classList.remove("open");
  });
  const toast=document.getElementById("toast");
  if(toast)setTimeout(()=>toast.style.display="none",4000);
  document.addEventListener("keydown",e=>{
    if(e.key==="n"&&!["INPUT","TEXTAREA","SELECT"].includes(document.activeElement.tagName))open();
    if(e.key==="Escape")close();
  });
});
