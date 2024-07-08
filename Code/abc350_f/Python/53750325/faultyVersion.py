from collections import deque


def swap_char(x, swap):
    if swap == 0:
        return x
    else:
        xu = x.upper()
        if x == xu:
            return x.lower()
        else:
            return xu


S = list(input())

left_to_right = dict()
d = deque()
for i, s in enumerate(S):
    if s == "(":
        d.append(i)
    if s == ")":
        left_to_right[d.pop()] = i
right_to_left = {v: k for k, v in left_to_right.items()}

print(left_to_right)
print(right_to_left)


i = 0
swap = 0
while i < len(S):
    if swap == 0:
        if S[i] == "(":
            i = left_to_right[i] - 1
            swap = 1
        elif S[i] == ")":
            i = right_to_left[i] - 1
            swap = 1
        else:
            print(S[i], end="")
            i += 1
    else:
        if S[i] == ")":
            i = right_to_left[i] + 1
            swap = 0
        elif S[i] == "(":
            i = left_to_right[i] + 1
            swap = 0
        else:
            print(swap_char(S[i], 1), end="")
            i -= 1

print("")
