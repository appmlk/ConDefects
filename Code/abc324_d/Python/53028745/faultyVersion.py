import sys
def main():
    input = sys.stdin.readline
    n = int(input())
    s = sorted(input()[:-1])
    max_val = int("".join(s[::-1]))
    s = list(map(int, s))
    cnt = 0
    for i in range(1,10**8+1):
        if i*i > max_val:
            break
        lst = list(map(int, str(i*i)))
        if len(lst) < n:
            lst += [0] * (n - len(lst))
        if sorted(lst) == s:
            cnt += 1
    print(cnt)        
main()
