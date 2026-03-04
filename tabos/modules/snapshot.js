import { loadConfig } from "./tabs.js";

export function initSnapshot() {
  chrome.runtime.onMessage.addListener((msg, sender, sendResponse) => {
    (async () => {
      if (msg?.type !== "SNAPSHOT_NOW") return sendResponse({ ok: false });

      const cfg = await loadConfig();
      if (!cfg.features.snapshotMode) return sendResponse({ ok: false, error: "Snapshot disabled" });

      const tabId = msg.tabId;
      if (typeof tabId !== "number") return sendResponse({ ok: false, error: "No tabId" });

      const data = await chrome.pageCapture.saveAsMHTML({ tabId });
      const url = URL.createObjectURL(data);

      // Open snapshot in a new tab (static)
      await chrome.tabs.create({ url, active: true });
      return sendResponse({ ok: true });
    })();

    return true;
  });
}
