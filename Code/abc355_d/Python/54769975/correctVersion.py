N = int(input())
starts = [0] * N
ends = [0] * N

for i in range(N):
    start, end = map(int, input().split())
    starts[i] = start
    ends[i] = end

starts.sort(reverse = True)
ends.sort(reverse = True)

n_active_intervals = 0
n_overlaps = 0
while starts:
    if starts[-1] <= ends[-1]:
        # print(f"start at {starts[-1]}, {n_active_intervals} active")
        starts.pop()
        n_overlaps += n_active_intervals
        n_active_intervals += 1
    else:
        # print(f"end at {ends[-1]},  {n_active_intervals-1} active")
        ends.pop()
        n_active_intervals -= 1

print(n_overlaps)