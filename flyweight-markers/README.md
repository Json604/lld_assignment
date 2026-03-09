Flyweight — Icon Symbol Optimization (Refactoring)
--------------------------------------------------
Narrative (Current Code)
A visualization tool called **ChartViz** displays thousands of data point icons.
Currently, each `MapMarker` duplicates its visual appearance properties (shape, color, size, filled).
With large datasets, this creates excessive memory overhead from redundant style objects.

Your Task
1) Create an immutable `MarkerStyle` class containing **intrinsic state** (shape, color, size, filled).
2) Build a `MarkerStyleFactory` that caches and reuses shared `MarkerStyle` instances.
3) Refactor `MapMarker` to separate:
   - Intrinsic state: `MarkerStyle` (shared)
   - Extrinsic state: `lat`, `lng`, `label` (unique per marker)
4) Modify `MapDataSource` to request styles from the factory instead of creating new instances.

Acceptance Criteria
- Rendering output remains identical (same markers displayed, same format).
- Style objects with matching configurations share the same instance
  (verify with `QuickCheck` — should show minimal unique style objects).
- `MarkerStyle` must be immutable (final fields, no mutation methods).
- `MapMarker` contains only position/label data plus a style reference.

Hints
- Cache styles using a `Map<String, MarkerStyle>` in the factory.
- Composite key format: `"SHAPE|COLOR|SIZE|FILLED"` (e.g., `"PIN|RED|12|T"`)

Build & Run
  cd flyweight-markers/src
  javac com/example/map/*.java
  java com.example.map.App

Repo intent
This is a **refactoring exercise**: the initial code works but wastes memory.
Apply Flyweight pattern to optimize memory usage while preserving functionality.
