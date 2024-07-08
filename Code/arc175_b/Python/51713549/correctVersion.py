def main():
    N, A, B = map(int, input().split())
    str = list(input())

    open_cnt = str.count('(')
    closed_cnt = str.count(')')

    # Calculate lower bound of minimum cost
    result = B * (abs(open_cnt - closed_cnt)) // 2

    tmp = 0
    if closed_cnt > open_cnt:
        for i in range(len(str)):
            if (str[i] == ')'):
                tmp += 1
                str[i] = '('
                if tmp == (closed_cnt - open_cnt)//2:
                    break
    elif open_cnt > closed_cnt:
        for i in range(len(str) - 1, -1, -1):
            if (str[i] == '('):
                tmp += 1
                str[i] = ')'
                if tmp == (open_cnt - closed_cnt)//2:
                    break

    cum_num = 0
    min_operator = 0

    for i in range(len(str)):
        if str[i] == '(':
            cum_num += 1
        elif str[i] == ')':
            cum_num -= 1
        min_operator = min(min_operator, cum_num)
    min_operator = min_operator * (-1)
    if min_operator % 2 == 0:
        tmp = min(B*(min_operator), A * (min_operator) // 2)
    else:
        tmp = min(B*(min_operator), A * (min_operator // 2)) + min(2*B, A)

    print(result+tmp)


if __name__ == '__main__':
    main()
