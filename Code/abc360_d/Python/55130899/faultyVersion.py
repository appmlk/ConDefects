from collections import deque
N, T = map(int, input().split())
S = input()
X = list(map(int, input().split()))

num_l_ants = S.count('0')

l_ants = [0] * num_l_ants
r_ants = [0] * (N - num_l_ants)

l = 0
r = 0
for i, (c, x) in enumerate(zip(S, X)):
    if c == '0':
        l_ants[l] = x
        l += 1
    else:
        r_ants[r] = x
        r += 1

l_ants.sort()
r_ants.sort()

first_crossings = [-1] * len(r_ants)

l = 0
q = deque()
left_end = -1
right_end = 0
count = 0
while True:
    while right_end < len(r_ants) and r_ants[right_end] < l_ants[l]:
        if r_ants[right_end] + T * 2 > l_ants[l]:
            first_crossings[right_end] = l
            q.append(right_end)
        right_end += 1
    
    if left_end == -1 and len(q) > 0:
        left_end = q.popleft()
    while left_end != -1 and r_ants[left_end] + T * 2 < l_ants[l]:
        count += l - 1 - first_crossings[left_end] + 1
        if len(q) > 0:
            left_end = q.popleft()
        else:
            left_end = -1

    l += 1
    if l >= len(l_ants):
        while left_end != -1:
            count += l - 1 - first_crossings[left_end] + 1
            if len(q) > 0:
                left_end = q.popleft()
            else:
                left_end = -1
        break

print(count)