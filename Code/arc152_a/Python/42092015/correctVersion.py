import sys

read = sys.stdin.read
readline = sys.stdin.readline
readlines = sys.stdin.readlines


def main():
    N, L, *A = map(int, read().split())

    no_more_two = False

    for a in A:
        if a == 1:
            L -= 2
        else:
            L -= 3
        
        if L <= 0:
            no_more_two = True

        if no_more_two and a == 2 and L != -1 and L != 0:
            print('No')
            break
    
    else:
        print('Yes')


if __name__ == '__main__':
    main()
