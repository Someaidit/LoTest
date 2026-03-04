(() => {
  const stopMedia = () => {
    for (const el of document.querySelectorAll("video,audio")) {
      try {
        el.pause();
        el.autoplay = false;
        el.removeAttribute("autoplay");
        el.preload = "none";
      } catch {}
    }
  };

  const patchPlay = () => {
    if (HTMLMediaElement.prototype.__tabosPatched) return;
    const orig = HTMLMediaElement.prototype.play;
    Object.defineProperty(HTMLMediaElement.prototype, "__tabosPatched", { value: true });

    HTMLMediaElement.prototype.play = function (...args) {
      // Allow user-initiated plays; block autoplay-ish calls when hidden
      if (document.visibilityState !== "visible") return Promise.resolve();
      return orig.apply(this, args);
    };
  };

  const apply = (enabled) => {
    if (!enabled) return;
    patchPlay();
    stopMedia();

    document.addEventListener("visibilitychange", () => {
      if (document.visibilityState !== "visible") stopMedia();
    });
  };

  chrome.storage.sync.get(["tabos_config"], (out) => {
    const cfg = out.tabos_config;
    const enabled = !!cfg?.features?.mediaSilencer && !!cfg?.media?.blockAutoplay;
    apply(enabled);
  });

  chrome.storage.onChanged.addListener((changes, area) => {
    if (area !== "sync") return;
    const cfg = changes.tabos_config?.newValue;
    const enabled = !!cfg?.features?.mediaSilencer && !!cfg?.media?.blockAutoplay;
    apply(enabled);
  });
})();
