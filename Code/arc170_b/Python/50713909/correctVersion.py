from collections import defaultdict
N = int(input())
A = list(map(int, input().split()))

single_last = [-1]*11
pair_last = [[-1]*11 for _ in range(11)]

triplet_dict = defaultdict(list)
for start in range(1,11):
    for delta in range(-4,5):
        if 1 <= start + 2*delta <= 10:
            triplet_dict[start+2*delta].append((start, start + delta))

#print(triplet_dict[1])
count = 0
last_idx = -1
for i in range(N):
    a = A[i]

    for val1, val2 in triplet_dict[a]:
        last_idx = max(last_idx, pair_last[val1][val2])
        #print(val1, val2, pair_last[val1][val2])
    if last_idx != -1:
        #print("here")
        count += last_idx + 1

    # update data
    for j in range(1,11):
        pair_last[j][a] = single_last[j]
    single_last[a] = i

    # print(single_last)
    # print("pl", pair_last[9][5])

print(count)