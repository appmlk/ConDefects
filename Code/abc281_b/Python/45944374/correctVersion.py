S = input()
if S[0].isupper() and S[-1].isupper():
    T = S[1:-1]
    if T.isdigit():
        if 100000 <= int(T) <= 999999 and len(T) == 6:
            print('Yes')
        else:
            print('No')
    else:
        print('No')
else:
    print('No')

