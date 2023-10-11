# -*- coding: utf-8 -*-


def main():
    import sys
    from collections import deque
    from typing import Any, List, Tuple

    input = sys.stdin.readline

    h, w = map(int, input().split())
    c = [list(input().rstrip()) for _ in range(h)]
    sy, sx = 0, 0

    def bfs_for_grid(
        grid: List[List[Any]], h: int, w: int, sy: int = 0, sx: int = 0
    ) -> Tuple[List[List[bool]], List[List[int]]]:
        d = deque()
        d.append((sy, sx))
        visited = [[False] * w for _ in range(h)]
        pending = -1
        dist = [[pending] * w for _ in range(h)]
        dist[sy][sx] = 1  # Initialize
        dxy = [(-1, 0), (1, 0), (0, -1), (0, 1)]

        while d:
            y, x = d.popleft()

            if dist[y][x] == pending:
                continue

            if visited[y][x]:
                continue

            visited[y][x] = True

            for dx, dy in dxy:
                nx = x + dx
                ny = y + dy

                if nx < 0 or nx >= w or ny < 0 or ny >= h:
                    continue
                if visited[ny][nx]:
                    continue
                if grid[ny][nx] == "#":
                    continue
                if dist[ny][nx] != pending and dist[ny][nx] <= dist[y][x]:
                    continue

                dist[ny][nx] = dist[y][x] + 1  # Update ans
                d.append((ny, nx))

        return visited, dist

    visited, dist = bfs_for_grid(grid=c, h=h, w=w, sy=sy, sx=sx)
    # print(dist)
    ans = 1

    for i in range(h):
        for j in range(w):
            ans = max(ans, dist[i][j])

    print(ans)


if __name__ == "__main__":
    main()
