function [status, stop] = checkagr( R, C, b, t )

    %% CHECKAGR
    % Check agreement and Pareto-optimality conditions
    % Input: R, remaining issues vector, dim: T x m
    %        C, global constraint values, dim: 1 x m
    %        b, best offer package, dim N x m
    %        t, current round (integer number)
    % Output: status, integer defining negotiation status
    %         possible values:
    %         - -1 no agreement because R < 0
    %         -  0 no agreement because exists i s.t. r_i < b_i
    %         -  1 agreement
    %         -  2 near Pareto-optimal agreement (R < 1%)
    %         -  3 Pareto-optimal agreement
    %        stop, integer defining our stopping condition
    %        possible values:
    %        - 0 no
    %        - 1 yes (R did not change in the last 5 rounds)

    rt = R(1:t, :);
    rt = rt(rt <= 0);
    po = size(rt, 1) ==  t * size(R, 2);

    if po && sum(R(t, :)) == 0
        status = 3;
    elseif po && sum(all(R(t,:) >= -10^-2))
        status = 2;
    elseif sum(all(R(t,:) >= 0))
        % if rp_i^t >= b_i^t for all i and t, then there's agreement
        status = 1;
        for i = 1:length(b)
            cp = C - sum(b) - b(i, :); % current ref. point
            if cp < b(i,:)
                status = 0;
                break;
            end
        end
    else
        status = -1;
    end

    % check if we can't go further
    stop = 0;
    if t > 4
        rx = sqrt(sum(R.^2, 2)); % R euclidean norm, element-wise
        stop_cond = abs(rx(t) - rx(t-4:t-1));
        if all(stop_cond <= 10^-15) % machine precision softener
            stop = 1;
        end
    end

end
