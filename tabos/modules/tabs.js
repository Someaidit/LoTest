import { DEFAULT_CONFIG } from "../config.js";

const KEY = "tabos_config";

export async function loadConfig() {
  return new Promise((resolve) => {
    chrome.storage.sync.get([KEY], (out) => resolve(out[KEY] ?? structuredClone(DEFAULT_CONFIG)));
  });
}

export async function saveConfig(cfg) {
  return new Promise((resolve) => {
    chrome.storage.sync.set({ [KEY]: cfg }, () => resolve());
  });
}

export function registerContextMenus() {
  chrome.contextMenus.removeAll(() => {
    chrome.contextMenus.create({
      id: "tabos_discard",
      title: "TabOS: Discard this tab (free memory)",
      contexts: ["page"]
    });

    chrome.contextMenus.create({
      id: "tabos_snapshot",
      title: "TabOS: Snapshot this page (static MHTML)",
      contexts: ["page"]
    });
  });

  chrome.contextMenus.onClicked.addListener(async (info, tab) => {
    if (!tab?.id) return;
    if (info.menuItemId === "tabos_discard") await chrome.tabs.discard(tab.id);
    if (info.menuItemId === "tabos_snapshot") {
      await chrome.runtime.sendMessage({ type: "SNAPSHOT_NOW", tabId: tab.id });
    }
  });
}

export function initTabFeatures() {
  // Placeholder: add future tab grouping logic here (domain/rules-based).
  // Keep it modular: one listener per feature.
}
