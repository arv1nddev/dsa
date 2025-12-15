# N-Sum Problem Pattern (Revision Notes)

## Core Insight

After solving **2-Sum, 3-Sum, and 4-Sum**, a clear general pattern emerges for the **N-Sum** family of problems:

> To solve an **N-Sum** problem efficiently on a sorted array, you typically use:
>
> * **(N − 2) nested loops** to fix indices
> * **2-pointer technique** for the remaining two elements

This reduces the brute-force (O(n^N)) approach to **(O(n^{N-1}))**.

---

## Why (N − 2) Loops?

Each loop **fixes one element** of the final N-tuple.

* After fixing **(N − 2)** elements
* You are left with finding **2 numbers** whose sum equals a target
* **2-Sum** can be solved in **O(n)** using two pointers on a sorted array

Hence:

```
(N − 2) fixed elements + 2-pointer search
```

---

## Time Complexity Derivation

Let `n` be the array size.

* Each fixed index loop → `O(n)`
* Number of such loops → `(N − 2)`
* Remaining 2-sum using two pointers → `O(n)`

Total complexity:

```
O(n^(N−2)) × O(n) = O(n^(N−1))
```

### Examples

| Problem | Fixed Loops | Technique                | Time Complexity |
| ------- | ----------- | ------------------------ | --------------- |
| 2-Sum   | 0           | Two pointers / Hashing   | O(n)            |
| 3-Sum   | 1           | Fix + Two pointers       | O(n²)           |
| 4-Sum   | 2           | Fix + Fix + Two pointers | O(n³)           |
| N-Sum   | N − 2       | Fix + … + Two pointers   | O(n^(N−1))      |

---

## General Algorithm Template

1. **Sort the array**
2. Use `(N − 2)` nested loops to fix indices
3. For the remaining subarray:

   * Apply **two pointers** (`left`, `right`)
   * Move pointers based on sum comparison
4. Skip duplicates at every level to avoid repeated combinations

---

## Pseudocode (Conceptual)

```text
sort(array)

for i1 in range:
  for i2 in range:
    ... (repeat N−2 times)
      left = next index
      right = end

      while left < right:
        sum = fixed elements + array[left] + array[right]

        if sum == target:
          record combination
          move left and right (skip duplicates)
        else if sum < target:
          left++
        else:
          right--
```

---

## Important Constraints & Notes

* Works **only on sorted arrays** (or after sorting)
* Duplicate handling is **mandatory** (skip equal adjacent values)
* Suitable when:

  * N is small (typically ≤ 4)
  * Input size is moderate

For large N, this approach becomes infeasible due to exponential growth.

---

## Mental Model (Very Important)

Think of N-Sum as:

> **Reduce N-Sum → (N − 1)-Sum → … → 2-Sum**

Each reduction fixes one index.

---

## Why Two Pointers at the End?

* 2-Sum on a sorted array is optimally solvable in **O(n)**
* This is what makes the entire pattern efficient
* Any attempt to fix more than `(N − 2)` indices leads back to brute force

---

## Key Takeaway (One Line)

> **N-Sum = (N − 2) fixed indices + 2-pointer sweep → O(n^(N−1))**

Use this as a mental shortcut during interviews and problem solving.
