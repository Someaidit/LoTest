(() => {
  const apply = (enabled) => {
    const id = "tabos_reduce_motion";
    let style = document.getElementById(id);

    if (!enabled) {
      style?.remove();
      return;
    }

    if (!style) {
      style = document.createElement("style");
      style.id = id;
      document.documentElement.appendChild(style);
    }

    style.textContent = `
      * { animation: none !important; transition: none !important; scroll-behavior: auto !important; }
      * { backdrop-filter: none !important; -webkit-backdrop-filter: none !important; }
    `;
  };

  chrome.storage.sync.get(["tabos_config"], (out) => {
    const cfg = out.tabos_config;
    const enabled = !!cfg?.features?.reduceMotion && !!cfg?.reduceMotion?.forceOff;
    apply(enabled);
  });

  chrome.storage.onChanged.addListener((changes, area) => {
    if (area !== "sync") return;
    const cfg = changes.tabos_config?.newValue;
    const enabled = !!cfg?.features?.reduceMotion && !!cfg?.reduceMotion?.forceOff;
    apply(enabled);
  });
})();
