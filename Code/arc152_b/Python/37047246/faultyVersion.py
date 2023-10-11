n, l = map(int, input().split())
a_list = list(map(int, input().split()))

ans_half = float('inf')
i = 0
j = n-1
while i < j:
    ai = a_list[i]
    aj = a_list[j]
    l_west = (aj-ai) + 2 * ai
    l_east = (aj-ai) + 2 * (l-aj)

    newans_half = max(l_west, l_east)
    ans_half = min(ans_half, newans_half)

    if l_east == l_west:
        break
    elif l_east < l_west:
        j -= 1
    else:
        i += 1

print(2 * ans_half)
