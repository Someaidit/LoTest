const featureList = [
  ["tabDiscard", "Tab discard"],
  ["burnerTabs", "Burner tabs"],
  ["mediaSilencer", "Media silencer"],
  ["reduceMotion", "Reduce motion"],
  ["snapshotMode", "Snapshot mode"],
  ["dnrTrackers", "Tracker blocking (DNR)"]
];

async function getConfig() {
  return new Promise((resolve) => {
    chrome.runtime.sendMessage({ type: "GET_CONFIG" }, (res) => resolve(res?.cfg));
  });
}

async function setFeature(feature, enabled) {
  return new Promise((resolve) => {
    chrome.runtime.sendMessage({ type: "SET_FEATURE", feature, enabled }, (res) => resolve(res));
  });
}

async function getActiveTab() {
  const [tab] = await chrome.tabs.query({ active: true, currentWindow: true });
  return tab;
}

(async () => {
  const toggles = document.getElementById("toggles");
  const cfg = await getConfig();

  for (const [key, label] of featureList) {
    const row = document.createElement("div");
    row.className = "row";

    const left = document.createElement("div");
    left.textContent = label;

    const right = document.createElement("input");
    right.type = "checkbox";
    right.checked = !!cfg.features[key];
    right.addEventListener("change", async () => {
      await setFeature(key, right.checked);
    });

    row.append(left, right);
    toggles.appendChild(row);
  }

  document.getElementById("discardActive").addEventListener("click", async () => {
    const tab = await getActiveTab();
    if (tab?.id) chrome.runtime.sendMessage({ type: "DISCARD_TAB", tabId: tab.id });
  });

  document.getElementById("snapshotActive").addEventListener("click", async () => {
    const tab = await getActiveTab();
    if (tab?.id) chrome.runtime.sendMessage({ type: "SNAPSHOT_TAB", tabId: tab.id });
  });
})();
