K = sorted(list(map(int, input().split())))

if len(set(K)) == 2 and (K[2] != K[3] or K[1] != K[2]):
    print('Yes')
else:
    print('No')
