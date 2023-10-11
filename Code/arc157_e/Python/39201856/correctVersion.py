def solve_sub(n, a, b, c, p_list):
    if n == 1:
        return "Yes"
    if c % 2 == 1:
        return "No"
    depth = [0] * n
    is_leaf = [1] * n
    parents = [0] * n
    children = [[] for _ in range(n)]
    for i, p in enumerate(p_list):
        depth[i + 1] = depth[p - 1] + 1
        is_leaf[p - 1] = 0
        parents[i + 1] = p - 1
        children[p - 1].append(i + 1)

    dp_x = [[-1] * (n // 2 + 2) for _ in range(n)]
    dp_y = [[-1] * (n // 2 + 2) for _ in range(n)]
    count_leaves = [0] * n
    # pを根として、leafにi個のYを書いたとしてあと何個Yを書けるか
    for p in range(n - 1, -1, -1):
        if is_leaf[p]:
            dp_x[p][0] = 0
            dp_y[p][1] = 0
            count_leaves[p] = 1
        else:
            q, r = children[p]
            count_leaves[p] = count_leaves[q] + count_leaves[r]
            for i in range(count_leaves[q] + 1):
                if dp_x[q][i] == -1:
                    continue
                for j in range(count_leaves[r] + 1):
                    if dp_x[r][j] == -1:
                        continue
                    dp_y[p][i + j] = max(dp_y[p][i + j], dp_x[q][i] + dp_x[r][j] + 1)

            for i in range(count_leaves[q] + 1):
                if max(dp_x[q][i], dp_y[q][i]) == -1:
                    continue
                for j in range(count_leaves[r] + 1):
                    if max(dp_x[r][j], dp_y[r][j]) == -1:
                        continue
                    dp_x[p][i + j] = max(dp_x[p][i + j], max(dp_x[q][i], dp_y[q][i]) + max(dp_x[r][j], dp_y[r][j]))

    # print(dp_x)
    # print(dp_y)
    # 根がXの場合
    # leafのYの個数
    y_leaf_count = b - (c // 2)
    y_non_leaf_count = c // 2
    if 0 <= y_leaf_count < n // 2 + 2:
        if dp_x[0][y_leaf_count] >= y_non_leaf_count:
            return "Yes"
    # 根がYの場合
    # leafのYの個数
    y_leaf_count = b - (c // 2) + 1
    y_non_leaf_count = c // 2
    if 0 <= y_leaf_count < n // 2 + 2:
        if dp_y[0][y_leaf_count] >= y_non_leaf_count:
            return "Yes"
    return "No"


def solve(t, case_list):
    res = []
    for n, a, b, c, p_list in case_list:
        res.append(solve_sub(n, a, b, c, p_list))
    # print(res)
    return res


def main():
    t = int(input())
    case_list = []
    for _ in range(t):
        n, a, b, c = map(int, input().split())
        p_list = list(map(int, input().split()))
        case_list.append((n, a, b, c, p_list))
    res = solve(t, case_list)
    for r in res:
        print(r)


def test():
    assert solve(3, [
        (7, 2, 2, 2, [1, 1, 2, 2, 3, 3]),
        (7, 0, 2, 4, [1, 1, 2, 2, 3, 3]),
        (7, 2, 0, 4, [1, 1, 2, 2, 4, 4]),
    ]) == ["Yes", "Yes", "No"]


if __name__ == "__main__":
    test()
    main()
